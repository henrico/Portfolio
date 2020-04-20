require('./favicon.ico');

import 'Notyf-js/dist/Notyf.min.css';

import './progress.css';

import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-nav-wizard/bootstrap-nav-wizard.min.css';
import './bootstrap.min.css';

import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import 'react-bootstrap-table2-filter/dist/react-bootstrap-table2-filter.min.css';

import './index.css';

import Content from './Content.js';

var server = '@restServerName@';
if (server === '@' + 'restServerName@') {
  server = 'http://localhost:8000';
}

$(document).ready(() => {
  $('body').append(
    $('<div/>', {
      id: 'app'
    })
  );
  ReactDOM.render(<Content />, document.getElementById('app'));
});
