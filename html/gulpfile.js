var gulp = require('gulp');

var clean = require('gulp-clean');
var mainFiles = require('npmfiles');
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

  return gulp.src(mainFiles()).pipe(gulp.dest('app/vendor'));
});

gulp.task('indexVendor', function() {
  var bowerStreamJS = gulp.src(mainFiles(), {read: false});

  bowerStreamJS = bowerStreamJS.pipe(gulp.dest('app/vendor'));

  return gulp.src('./app/index.html').pipe(inject(bowerStreamJS, {
    relative: true,
    name: 'vendor'
  })).pipe(gulp.dest('./app'));
});

gulp.task('index', function() {
  var target = gulp.src('./app/index.html');
  var sources = gulp.src([
    './app/src/**/*.js', './app/src/**/*.css'
  ], {read: false});

  return target.pipe(inject(sources, {relative: true})).pipe(gulp.dest('./app'));
});

gulp.task('build', gulp.series('index', 'indexVendor'));
