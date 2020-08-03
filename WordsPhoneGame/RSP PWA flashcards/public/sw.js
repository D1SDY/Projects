//File can be named as preferred
//Can trigger different events(even push notifications?)
//SW used for caching -> special cache


//does not console log???
//precache static assets of page
console.log(self);
self.addEventListener('install', function(event) {
    console.log('ServiceWorker installed');
    event.waitUntil(caches.open('static')
        .then(function(cache) {
            // cache.add('/');
            // cache.add('/index.html');
            // cache.add('/src/js/app.js');
            cache.addAll([
                '/',
                '/index.html',
                '/start.html',
                '/add.html',
                '/cards.html',
                '/src/css/card.css',
                '/src/css/style.css',
                '/src/js/controllers/addPageController.js',
                '/src/js/controllers/startPageController.js',
                '/src/js/models/categoryAddModel.js',
                '/src/js/models/wordAddModel.js',
                '/src/js/models/startPageModel.js',
                '/src/js/data.js',
                '/src/js/cardCreation.js',
                '/src/images/firework.png',
                '/src/images/homeBtn.png',
                '/src/js/app.js',
            ]);
        })
    );
});

self.addEventListener('activate', function() {
    console.log('ServiceWorker activated');
});

/*
FETCH event.
if we have request in the cache we get the response from the cache,
if we don't find it there => we make a network request
*/
self.addEventListener('fetch', function(event) {
  if (event.request.url.startsWith('https://basic-pwa-4124d.firebaseapp.com/cards.html')) {
      event.respondWith(caches.match('/cards.html')
          .then(function(res) {
              if(res) {
                  return res;
              }
            })
        )} else {
             console.log("22222")
              event.respondWith(
                  caches.match(event.request)
                      .then(function(res) {

                         if(res) {
                              return res;
                          }


                          else {
                              return fetch(event.request);
                          }
                      })
              )};
      });
