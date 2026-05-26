(function () {
  'use strict';

  function detectContextPath() {
    const scripts = document.querySelectorAll('script[src*="app.js"]');
    if (!scripts.length) return '';
    const src = scripts[scripts.length - 1].src;
    const match = src.match(/^(.*)\/frontend\/js\/app\.js(?:\?.*)?$/);
    return match ? match[1] : '';
  }

  const ctx = detectContextPath();

  function apiUrl(path) {
    return ctx + '/api' + path;
  }

  function servletUrl(path) {
    return ctx + path;
  }

  async function fetchJson(path) {
    const res = await fetch(apiUrl(path), { credentials: 'same-origin' });
    if (res.status === 401) {
      window.location.href = ctx + '/frontend/login.html';
      return null;
    }
    if (!res.ok) throw new Error('API error: ' + res.status);
    return res.json();
  }

  function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text == null ? '' : String(text);
    return div.innerHTML;
  }

  function statusBadge(status) {
    const s = (status || '').toLowerCase();
    const labels = {
      pending: '⏳ Pending',
      borrowed: '📥 Borrowed',
      returned: '✅ Returned',
      rejected: '❌ Rejected',
      overdue: '⚠️ Overdue',
      active: '✅ Active',
      suspended: '🔒 Suspended',
      paid: '✅ Paid',
      unpaid: '🔴 Unpaid'
    };
    return labels[s] || escapeHtml(status);
  }

  async function loadSession() {
    const data = await fetchJson('/session');
    if (!data || !data.authenticated) return null;
    return data;
  }

  function injectUserBar(session) {
    const bar = document.getElementById('userBar');
    if (!bar || !session) return;
    bar.innerHTML =
      '<span>👋 ' +
      escapeHtml(session.fullName || session.username) +
      '</span> ' +
      '<a class="secondary-btn" href="' +
      servletUrl('/logout') +
      '">Logout</a>';
  }

  async function initStudentBooks() {
    const tbody = document.querySelector('#booksTable tbody');
    if (!tbody) return;
    const books = await fetchJson('/books');
    if (!books) return;
    tbody.innerHTML = '';
    books.forEach(function (b) {
      const tr = document.createElement('tr');
      tr.innerHTML =
        '<td><strong>' +
        escapeHtml(b.title) +
        '</strong></td>' +
        '<td>' +
        escapeHtml(b.author) +
        '</td>' +
        '<td>' +
        escapeHtml(b.category) +
        '</td>' +
        '<td>' +
        escapeHtml(b.availableQuantity) +
        '</td>' +
        '<td>' +
        '<form action="' +
        servletUrl('/borrow') +
        '" method="post">' +
        '<input type="hidden" name="bookId" value="' +
        b.bookId +
        '">' +
        '<button type="submit" class="btn" style="padding:6px 12px;font-size:0.9em"' +
        (b.availableQuantity < 1 ? ' disabled' : '') +
        '>📥 Borrow</button></form></td>';
      tbody.appendChild(tr);
    });
  }

  async function initStudentMyBooks() {
    const tbody = document.querySelector('#myBooksTable tbody');
    if (!tbody) return;
    const records = await fetchJson('/mybooks');
    if (!records) return;
    tbody.innerHTML = '';
    records.forEach(function (r) {
      const tr = document.createElement('tr');
      tr.innerHTML =
        '<td><strong>' +
        escapeHtml(r.title) +
        '</strong></td>' +
        '<td>' +
        escapeHtml(r.borrowDate) +
        '</td>' +
        '<td>' +
        escapeHtml(r.dueDate) +
        '</td>' +
        '<td>' +
        statusBadge(r.status) +
        '</td>' +
        '<td>—</td>';
      tbody.appendChild(tr);
    });
  }

  async function initLibrarianAllBorrows() {
    const tbody = document.querySelector('#borrowsTable tbody');
    if (!tbody) return;
    const records = await fetchJson('/allborrows');
    if (!records) return;
    tbody.innerHTML = '';
    records.forEach(function (r) {
      const tr = document.createElement('tr');
      let actions = '—';
      if (r.status === 'pending') {
        actions =
          '<form action="' +
          servletUrl('/approve') +
          '" method="post" style="display:inline">' +
          '<input type="hidden" name="borrowId" value="' +
          r.borrowId +
          '">' +
          '<button type="submit" class="btn" style="padding:6px 12px;font-size:0.9em">✅ Approve</button></form> ' +
          '<form action="' +
          servletUrl('/reject') +
          '" method="post" style="display:inline">' +
          '<input type="hidden" name="borrowId" value="' +
          r.borrowId +
          '">' +
          '<button type="submit" class="secondary-btn" style="padding:6px 12px;font-size:0.9em">❌ Reject</button></form>';
      } else if (r.status === 'borrowed') {
        actions =
          '<form action="' +
          servletUrl('/return') +
          '" method="post">' +
          '<input type="hidden" name="borrowId" value="' +
          r.borrowId +
          '">' +
          '<button type="submit" class="btn" style="padding:6px 12px;font-size:0.9em">↩️ Return</button></form>';
      }
      tr.innerHTML =
        '<td>' +
        escapeHtml(r.username) +
        '</td>' +
        '<td><strong>' +
        escapeHtml(r.title) +
        '</strong></td>' +
        '<td>' +
        escapeHtml(r.borrowDate) +
        '</td>' +
        '<td>' +
        escapeHtml(r.dueDate) +
        '</td>' +
        '<td>' +
        statusBadge(r.status) +
        '</td>' +
        '<td>' +
        actions +
        '</td>';
      tbody.appendChild(tr);
    });
  }

  async function initLibrarianManageBooks() {
    const tbody = document.querySelector('#inventoryTable tbody');
    if (!tbody) return;
    const books = await fetchJson('/books');
    if (!books) return;
    tbody.innerHTML = '';
    books.forEach(function (b) {
      const tr = document.createElement('tr');
      tr.innerHTML =
        '<td>' +
        b.bookId +
        '</td>' +
        '<td>' +
        escapeHtml(b.title) +
        '</td>' +
        '<td>' +
        escapeHtml(b.author) +
        '</td>' +
        '<td>' +
        escapeHtml(b.category) +
        '</td>' +
        '<td>' +
        escapeHtml(b.availableQuantity) +
        '</td>' +
        '<td>' +
        '<form action="' +
        servletUrl('/managebooks') +
        '" method="post" onsubmit="return confirm(\'Delete this book?\')">' +
        '<input type="hidden" name="action" value="delete">' +
        '<input type="hidden" name="bookId" value="' +
        b.bookId +
        '">' +
        '<button type="submit" class="secondary-btn" style="padding:6px 12px;font-size:0.9em">Delete</button></form></td>';
      tbody.appendChild(tr);
    });
  }

  async function initLibrarianPenalties() {
    const tbody = document.querySelector('#penaltiesTable tbody');
    if (!tbody) return;
    const rows = await fetchJson('/penalties');
    if (!rows) return;
    tbody.innerHTML = '';
    rows.forEach(function (p) {
      const tr = document.createElement('tr');
      tr.innerHTML =
        '<td>' +
        escapeHtml(p.penaltyId) +
        '</td>' +
        '<td>' +
        escapeHtml(p.username) +
        '</td>' +
        '<td><strong>' +
        escapeHtml(p.bookTitle) +
        '</strong></td>' +
        '<td>$' +
        escapeHtml(p.amount) +
        '</td>' +
        '<td>' +
        escapeHtml(p.reason) +
        '</td>' +
        '<td>' +
        statusBadge(p.status) +
        '</td>';
      tbody.appendChild(tr);
    });
  }

  async function initManagerUsers() {
    const tbody = document.querySelector('#usersTable tbody');
    if (!tbody) return;
    const users = await fetchJson('/users');
    if (!users) return;
    tbody.innerHTML = '';
    users.forEach(function (u) {
      const tr = document.createElement('tr');
      let action =
        u.status === 'active'
          ? '<form action="' +
            servletUrl('/manageusers') +
            '" method="post" style="display:inline">' +
            '<input type="hidden" name="action" value="lock">' +
            '<input type="hidden" name="userId" value="' +
            u.userId +
            '">' +
            '<button type="submit" class="secondary-btn" style="padding:6px 12px;font-size:0.9em">Lock</button></form>'
          : '<form action="' +
            servletUrl('/manageusers') +
            '" method="post" style="display:inline">' +
            '<input type="hidden" name="action" value="unlock">' +
            '<input type="hidden" name="userId" value="' +
            u.userId +
            '">' +
            '<button type="submit" class="btn" style="padding:6px 12px;font-size:0.9em">Unlock</button></form>';
      action +=
        ' <form action="' +
        servletUrl('/manageusers') +
        '" method="post" style="display:inline" onsubmit="return confirm(\'Delete user?\')">' +
        '<input type="hidden" name="action" value="delete">' +
        '<input type="hidden" name="userId" value="' +
        u.userId +
        '">' +
        '<button type="submit" class="secondary-btn" style="padding:6px 12px;font-size:0.9em">Delete</button></form>';
      tr.innerHTML =
        '<td>' +
        u.userId +
        '</td>' +
        '<td><strong>' +
        escapeHtml(u.username) +
        '</strong></td>' +
        '<td>' +
        escapeHtml(u.email) +
        '</td>' +
        '<td>' +
        escapeHtml(u.role) +
        '</td>' +
        '<td>' +
        statusBadge(u.status) +
        '</td>' +
        '<td>' +
        action +
        '</td>';
      tbody.appendChild(tr);
    });
  }

  async function initManagerLogs() {
    const tbody = document.querySelector('#logsTable tbody');
    if (!tbody) return;
    const logs = await fetchJson('/logs');
    if (!logs) return;
    tbody.innerHTML = '';
    logs.forEach(function (l) {
      const tr = document.createElement('tr');
      tr.innerHTML =
        '<td><strong>' +
        escapeHtml(l.username || 'system') +
        '</strong></td>' +
        '<td>' +
        escapeHtml(l.action) +
        '</td>' +
        '<td>' +
        escapeHtml(l.details) +
        '</td>' +
        '<td>' +
        escapeHtml(l.logTime) +
        '</td>';
      tbody.appendChild(tr);
    });
  }

  async function initManagerStats(container) {
    if (!container) return;
    const stats = await fetchJson('/stats');
    if (!stats) return;
    container.innerHTML =
      '<p style="font-size:1.1em;">' +
      '<strong style="color:var(--primary);">👥 Total Users:</strong> ' +
      stats.totalUsers +
      '<br>' +
      '<strong style="color:var(--primary);">📚 Total Books:</strong> ' +
      stats.totalBooks +
      '<br>' +
      '<strong style="color:var(--warning);">📤 Borrowed:</strong> ' +
      stats.borrowedBooks +
      '<br>' +
      '<strong style="color:var(--warning);">⏳ Pending:</strong> ' +
      stats.pendingRequests +
      '</p>';
  }

  async function initManagerReport() {
    const grid = document.getElementById('reportGrid');
    if (!grid) return;
    const stats = await fetchJson('/report');
    if (!stats) return;
    grid.innerHTML =
      '<div class="page-panel" style="background:linear-gradient(135deg,rgba(99,102,241,0.1),rgba(118,75,162,0.1));border-left:4px solid var(--primary);">' +
      '<h3 style="margin-top:0;">👥 Total Users</h3>' +
      '<p style="font-size:2.5em;margin:0;color:var(--primary);font-weight:bold;">' +
      stats.totalUsers +
      '</p></div>' +
      '<div class="page-panel" style="background:linear-gradient(135deg,rgba(245,158,11,0.1),rgba(245,158,11,0.05));border-left:4px solid var(--warning);">' +
      '<h3 style="margin-top:0;color:var(--warning);">📚 Total Books</h3>' +
      '<p style="font-size:2.5em;margin:0;color:var(--warning);font-weight:bold;">' +
      stats.totalBooks +
      '</p></div>' +
      '<div class="page-panel" style="background:linear-gradient(135deg,rgba(239,68,68,0.1),rgba(239,68,68,0.05));border-left:4px solid var(--danger);">' +
      '<h3 style="margin-top:0;color:var(--danger);">📤 Borrowed Books</h3>' +
      '<p style="font-size:2.5em;margin:0;color:var(--danger);font-weight:bold;">' +
      stats.borrowedBooks +
      '</p></div>';
  }

  function showLoginError() {
    const params = new URLSearchParams(window.location.search);
    if (!params.has('error')) return;
    const box = document.getElementById('loginError');
    if (box) {
      box.textContent =
        params.get('error') === 'role'
          ? 'Your account role is not supported.'
          : 'Invalid username or password.';
      box.style.display = 'block';
    }
  }

  document.addEventListener('DOMContentLoaded', async function () {
    showLoginError();

    const page = document.body.dataset.page;
    if (!page || page === 'login' || page === 'home') return;

    const session = await loadSession();
    if (!session) return;

    injectUserBar(session);

    switch (page) {
      case 'student-books':
        await initStudentBooks();
        break;
      case 'student-mybooks':
        await initStudentMyBooks();
        break;
      case 'librarian-allborrows':
        await initLibrarianAllBorrows();
        break;
      case 'librarian-managebooks':
        await initLibrarianManageBooks();
        break;
      case 'librarian-penalties':
        await initLibrarianPenalties();
        break;
      case 'manager-users':
        await initManagerUsers();
        break;
      case 'manager-logs':
        await initManagerLogs();
        break;
      case 'manager-dashboard':
        await initManagerStats(document.getElementById('managerStats'));
        break;
      case 'manager-report':
        await initManagerReport();
        break;
    }
  });
})();
