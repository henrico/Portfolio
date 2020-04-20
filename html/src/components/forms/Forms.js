import './Forms.css';

import BootstrapTable from 'react-bootstrap-table-next';
import filterFactory from 'react-bootstrap-table2-filter';
const NProgress = require('nprogress/nprogress.js');

import server from '../../Server.js';

import { MdError } from 'react-icons/md';
import { FaCheckCircle } from 'react-icons/fa';

var DatePicker = require('react-16-bootstrap-date-picker');

export class FormValidator {
  constructor(callback) {
    this.state = {};
    this.validators = {};
    this.callback = callback;
  }

  reset = name => {
    this.state[name] = {
      valid: false,
      touched: false,
      value: ''
    };
  };

  createValidation = (name, validation) => {
    this.state[name] = {
      valid: false,
      touched: false,
      value: ''
    };
    this.validators[name] = value => {
      var valid = validation(value);
      this.state[name].valid = valid === '';
      return valid;
    };
  };

  validate = name => {
    let ret = this.validators[name](this.state[name].value);
    this.state[name].valid = ret === '';

    this.updateForm();

    return ret;
  };

  updateForm = () => {
    var valid = true;
    var touched = true;

    var touchedValid = true;

    for (let item in this.state) {
      if (!this.state[item].valid) valid = false;
      if (!this.state[item].touched) touched = false;
      if (this.state[item].touched && !this.state[item].valid)
        touchedValid = false;
    }

    this.callback(valid, touched, touchedValid);
  };

  setValue = (name, value) => {
    this.state[name].touched = true;
    this.state[name].value = value;
  };

  isTouched = name => {
    return this.state[name].touched;
  };
}

export class TextField extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      value: this.props.value
    };
  }

  changeValue = e => {
    this.props.change(e.target.value);
    this.props.validator.setValue(this.props.validation, e.target.value);
    this.setState({
      value: e.target.value
    });
  };

  render() {
    var valid = this.props.validator.validate(this.props.validation);
    var touched = this.props.validator.isTouched(this.props.validation);
    var isValid = !(valid && touched);
    var sclass = '';

    this.props.validator.valid = isValid;

    if (touched) {
      if (isValid) {
        sclass = ' has-success has-feedback';
      } else {
        sclass = ' has-error has-feedback';
      }
    }

    return (
      <div class={'form-group' + sclass}>
        <label class="control-label col-md-2" for={this.props.id}>
          {this.props.label}
        </label>
        <div class="col-sm-10">
          <input
            class="form-control"
            id={this.props.id}
            placeholder={this.props.placeholder}
            onChange={this.changeValue}
            value={this.state.value}
            disabled={this.props.disabled}
          />
          {(() => {
            if (!isValid) {
              return (
                <span class="form-control-feedback">
                  <MdError />
                </span>
              );
            } else if (touched) {
              return (
                <span class="form-control-feedback">
                  <FaCheckCircle />
                </span>
              );
            }
          })()}

          {(() => {
            if (!isValid) {
              return (
                <div class="form-error">
                  <div>{valid}</div>
                </div>
              );
            }
          })()}
        </div>
      </div>
    );
  }
}

export class DateField extends React.Component {
  constructor(props) {
    super(props);

    //var date = this.props.value !== '' ? new Date(this.props.value) : '';

    this.state = {
      value: this.props.value
    };
  }

  formatDate = date => {
    var d = new Date(date),
      month = '' + (d.getMonth() + 1),
      day = '' + d.getDate(),
      year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
  };

  handleChange = date => {
    let newDate = date == null ? '' : this.formatDate(date);

    if (date != null) {
      if (new Date(date).getTime() < new Date().getTime())
        date = this.formatDate(new Date());
    }

    this.props.validator.setValue(this.props.validation, newDate);
    this.props.change(newDate);
    this.setState({
      value: date
    });
  };

  render() {
    var valid = this.props.validator.validate(this.props.validation);
    var touched = this.props.validator.isTouched(this.props.validation);
    var isValid = !(valid && touched);
    var sclass = '';

    this.props.validator.valid = isValid;

    if (touched) {
      if (isValid) {
        sclass = ' has-success has-feedback';
      } else {
        sclass = ' has-error has-feedback';
      }
    }

    return (
      <div class={'form-group' + sclass}>
        <label class="control-label col-md-2" for={this.props.id}>
          {this.props.label}
        </label>
        <div class="col-sm-10">
          <DatePicker
            id="example-datepicker"
            value={this.state.value}
            onChange={this.handleChange}
            dateFormat="YYYY-MM-DD"
            minDate={this.formatDate(new Date())}
            disabled={this.props.disabled}
          />
          {(() => {
            if (!isValid) {
              return (
                <span class="form-control-feedback">
                  <MdError />
                </span>
              );
            } else if (touched) {
              return (
                <span class="form-control-feedback">
                  <FaCheckCircle />
                </span>
              );
            }
          })()}

          {(() => {
            if (!isValid) {
              return (
                <div class="form-error">
                  <div>{valid}</div>
                </div>
              );
            }
          })()}
        </div>
      </div>
    );
  }
}

export class NumberField extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      value: this.props.value
    };
  }

  changeValue = e => {
    let val = e.target.value;

    if (this.props.noDecimals) {
      val = val.replace('.', '');
    }

    this.props.change(val);
    this.props.validator.setValue(this.props.validation, val);
    this.setState({
      value: val
    });
  };

  render() {
    var valid = this.props.validator.validate(this.props.validation);
    var touched = this.props.validator.isTouched(this.props.validation);
    var isValid = !(valid && touched);
    var sclass = '';

    this.props.validator.valid = isValid;

    if (touched) {
      if (isValid) {
        sclass = ' has-success has-feedback';
      } else {
        sclass = ' has-error has-feedback';
      }
    }

    return (
      <div class={'form-group' + sclass}>
        <label class="control-label col-md-2" for={this.props.id}>
          {this.props.label}
        </label>
        <div class="col-sm-10">
          <input
            type="number"
            class="form-control"
            id={this.props.id}
            onChange={this.changeValue}
            value={this.state.value}
            disabled={this.props.disabled}
          />
          {(() => {
            if (!isValid) {
              return (
                <span class="form-control-feedback">
                  <MdError />
                </span>
              );
            } else if (touched) {
              return (
                <span class="form-control-feedback">
                  <FaCheckCircle />
                </span>
              );
            }
          })()}

          {(() => {
            if (!isValid) {
              return (
                <div class="form-error">
                  <div>{valid}</div>
                </div>
              );
            }
          })()}
        </div>
      </div>
    );
  }
}

export class SelectField extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      value: this.props.value
    };
  }

  setOption = event => {
    this.props.validator.setValue(this.props.validation, event.target.value);
    this.setState({ value: event.target.value });
    this.props.setOption(event.target.value);
  };

  render() {
    var valid = this.props.validator.validate(this.props.validation);
    var touched = this.props.validator.isTouched(this.props.validation);
    var isValid = !(valid && touched);
    var sclass = '';

    this.props.validator.valid = isValid;

    if (touched) {
      if (isValid) {
        sclass = ' has-success has-feedback';
      } else {
        sclass = ' has-error has-feedback';
      }
    }

    return (
      <div class={'form-group' + sclass}>
        <label class="control-label col-md-2" for={this.props.id}>
          {this.props.label}
        </label>
        <div class="col-sm-10">
          <select
            class="form-control"
            onChange={this.setOption}
            id={this.props.id}
            disabled={this.props.disabled}
            value={this.state.value}
          >
            <option />
            {this.props.data.map(object => (
              <option value={object[this.props.keyField]}>
                {object[this.props.textField]}
              </option>
            ))}
          </select>
          {(() => {
            if (!isValid) {
              return (
                <span class="form-control-feedback">
                  <MdError />
                </span>
              );
            } else if (touched) {
              return (
                <span class="form-control-feedback">
                  <FaCheckCircle />
                </span>
              );
            }
          })()}

          {(() => {
            if (!isValid) {
              return (
                <div class="form-error">
                  <div>{valid}</div>
                </div>
              );
            }
          })()}
        </div>
      </div>
    );
  }
}

export class IncludeField extends React.Component {
  constructor(props) {
    super(props);

    const columns = this.props.columns.slice();

    columns.push({
      dataField: '',
      text: this.props.includeLabel,
      formatter: (cell, row) => {
        return (
          <input
            type="checkbox"
            checked={row.checked}
            onChange={e => {
              this.addRemoveProduct(e, row);
            }}
            disabled={this.props.disabled}
          />
        );
      }
    });

    this.state = {
      tableKey: 0,
      data: [],
      columns: columns
    };
  }

  addRemoveProduct = (e, row) => {
    const add = e.target.checked;

    const fields = this.props.entity[this.props.includedField];

    if (add) {
      var exist = false;

      for (var z = 0; z < fields.length; z++) {
        if (fields[z][this.props.keyField] === row[this.props.keyField])
          exist = true;
        break;
      }

      if (!exist) {
        row.checked = true;
        fields.push(row);
      }
    } else {
      for (var z = 0; z < fields.length; z++) {
        if (fields[z][this.props.keyField] === row[this.props.keyField]) {
          fields.splice(z, 1);
          return;
        }
      }
      row.checked = false;
    }

    this.setState({
      tableKey: this.state.tableKey + 1
    });
  };

  componentDidMount = () => {
    var self = this;

    NProgress.start();

    $.ajax({
      url: server + this.props.rest,
      type: 'GET',
      async: false,
      cache: false,
      timeout: 30000,
      success: function(result) {
        NProgress.done();
        self.applyCheckedFromEntity(result);
        self.setState({
          tableKey: self.state.tableKey++,
          data: result
        });
      },
      error: function(result) {
        Notyf.alert('Oops! Something went wrong!');
        NProgress.done();
      }
    });
  };

  applyCheckedFromEntity(data) {
    data.forEach(item => {
      const fields = this.props.entity[this.props.includedField];
      for (var z = 0; z < fields.length; z++) {
        if (fields[z][this.props.keyField] === item[this.props.keyField]) {
          item.checked = true;
          return;
        }
      }
      item.check = false;
    });
  }

  render() {
    return (
      <div class="form-group">
        <label class="control-label col-md-2" for={this.props.id}>
          {this.props.label}
        </label>
        <div class="col-sm-10">
          <BootstrapTable
            key={this.state.tableKey}
            keyField={this.props.keyField}
            data={this.state.data}
            columns={this.state.columns}
            filter={filterFactory()}
          />
        </div>
      </div>
    );
  }
}

export class FormHeading extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return <h4>{this.props.heading}</h4>;
  }
}

export class UpdateFormButton extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <button
        class={
          'btn btn-success pull-right' +
          (!this.props.data.enabled || !this.props.data.valid
            ? ' disabled'
            : '')
        }
        disabled={!this.props.data.enabled || !this.props.data.valid}
        onClick={() => this.props.update(this.props.data)}
      >
        Update
      </button>
    );
  }
}

export class CreateFormButton extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <button
          class={
            'btn btn-success pull-right' +
            (!this.props.enabled ? ' disabled' : '')
          }
          disabled={!this.props.enabled}
          onClick={() => this.props.add(this.props.data)}
        >
          Add
        </button>

        <div class="pull-right">&nbsp;</div>

        <button class="btn btn-default pull-right" onClick={this.props.cancel}>
          Cancel
        </button>
      </div>
    );
  }
}
