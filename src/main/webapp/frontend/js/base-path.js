(function () {
  var path = window.location.pathname;
  var marker = '/frontend/';
  var idx = path.indexOf(marker);
  var basePath;

  if (idx === -1) {
    var ctx = path.endsWith('/') ? path : path.substring(0, path.lastIndexOf('/') + 1);
    basePath = ctx + 'frontend/';
  } else {
    var tail = path.substring(idx + marker.length);
    var slash = tail.lastIndexOf('/');
    if (slash === -1) {
      basePath = path.substring(0, idx + marker.length);
    } else {
      basePath = path.substring(0, idx + marker.length) + tail.substring(0, slash + 1);
    }
  }

  var base = document.createElement('base');
  base.href = basePath;
  document.head.prepend(base);
})();
