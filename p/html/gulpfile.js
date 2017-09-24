var gulp = require('gulp');

var gls = require('gulp-live-server');
var clean = require('gulp-clean');
var mainBowerFiles = require('gulp-main-bower-files');
var inject = require('gulp-inject');
var less = require('gulp-less');
var path = require('path');
var gulpSequence = require('gulp-sequence')

gulp.task('less', function() {
  return gulp.src('app/vendor/**/*.less').pipe(less({})).pipe(gulp.dest('app/vendor'));
});

gulp.task('clean', function(c) {
  return gulp.src('app/vendor/**/*', {read: false}).pipe(clean());
});

gulp.task('vendor', function() {

  var target = gulp.src('./app/index.html');
  var sources = gulp.src([
    './app/vendor/**/*.js', './app/vendor/**/*.css'
  ], {read: false});

  return gulp.src('./bower.json').pipe(mainBowerFiles()).pipe(gulp.dest('app/vendor'));
});

gulp.task('indexVendor', function() {
console.log("####################");
  var bowerStreamJS = gulp.src('./bower.json').pipe(mainBowerFiles(), {read: false});


  // var bowerStreamCSS = gulp.src(mainBowerFiles('**/*.css'));

  bowerStreamJS = bowerStreamJS.pipe(gulp.dest('app/vendor'));
  // bowerStreamCSS = bowerStreamCSS.pipe(gulp.dest('app/vendor'));

  return gulp.src('./app/index.html').pipe(inject(bowerStreamJS, {
    relative: true,
    name: 'vendor'
  // })).pipe(inject(bowerStreamCSS, {
  //   relative: true,
  //   name: 'vendor'
  })).pipe(gulp.dest('./app'));

  // var target = gulp.src('./app/index.html');
  // var sources = gulp.src(mainBowerFiles( ), {read: false});
  //
  // return target.pipe(inject(sources,{relative: true, name: 'vendor'}))
  //   .pipe(gulp.dest('./app'));
});

gulp.task('index', function() {
  var target = gulp.src('./app/index.html');
  var sources = gulp.src([
    './app/src/**/*.js', './app/src/**/*.css'
  ], {read: false});

  return target.pipe(inject(sources, {relative: true})).pipe(gulp.dest('./app'));
});

gulp.task('server', function() {

  var server = gls.static(['app'], 8888);
  server.start();

  //use gulp.watch to trigger server actions(notify, start or stop)
  gulp.watch([
    '**/*.css', '**/*.html'
  ], function(file) {
    server.notify.apply(server, [file]);
  });
});

gulp.task('serve', gulpSequence('index', 'indexVendor', 'server'));
