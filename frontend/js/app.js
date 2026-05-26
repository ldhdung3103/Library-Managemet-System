// Minimal frontend behavior for demo navigation
document.addEventListener('DOMContentLoaded', function(){
  const loginForm = document.getElementById('loginForm');
  if(loginForm){
    loginForm.addEventListener('submit', function(e){
      e.preventDefault();
      const role = document.getElementById('roleSelect').value;
      if(role === 'student') location.href = 'student/dashboard.html';
      if(role === 'librarian') location.href = 'librarian/dashboard.html';
      if(role === 'manager') location.href = 'manager/dashboard.html';
    });
  }
});