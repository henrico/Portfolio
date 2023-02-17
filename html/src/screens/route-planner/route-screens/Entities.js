import BootstrapTable from 'react-bootstrap-table-next';

import EntityIntroduction from './introduction/EntityIntroduction.js';

import { Table } from '../../../components/Tables.js';

import {
  FormHeading,
  UpdateFormButton,
  CreateFormButton
} from '../../../components/forms/Forms.js';

import server from '../../../Server.js';

import { FaPlus } from 'react-icons/fa';

import './Entities.css';

const NProgress = require('nprogress/nprogress.js');

import notyf from '../../../Notifications.js';

export default class Entities extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      data: [],
      tableKey: 0,
      newKey: 0,
      expandedMap: new Map(),
      adding: false,
      newEntity: this.props.newEntity()
    };
  }

  updateEntity = update => {
    var self = this;

    NProgress.start();
    $.ajax({
      type: 'PUT',
      url: server + self.props.rest +'/'+ update[this.props.keyField],
      contentType: 'application/json',
      data: JSON.stringify(update),
      success: function(result) {
        NProgress.done();
        notyf.confirm(self.props.entityName + ' saved.');
        self.setState({
          data: result,
          tableKey: self.state.tableKey + 1
        });
      },
      error: function(result) {
        NProgress.done();
        notyf.alert('Oops! Something went wrong!');
      }
    });
  };

  expandRow = () => {
    var self = this;

    return update => {
      var validate = (valid, touched, touchedValid) => {
        if (update.valid != ((touched && valid) || touchedValid)) {
          update.valid = (touched && valid) || touchedValid;
          this.setState({});
        }
      };
      var updateForm = self.props.form(
        update[this.props.keyField],
        update,
        'key',
        validate
      );

      return (
        <div class="container update-form">
          <div class="row">
            <div class="col-md-10">
              <FormHeading heading={'Editing ' + self.props.entityName} />
              {updateForm}
              <UpdateFormButton data={update} update={self.updateEntity} />
            </div>
          </div>
        </div>
      );
    };
  };

  delete = update => {
    var self = this;

    NProgress.start();
    $.ajax({
      type: 'DELETE',
      url: server + self.props.rest +'/'+ update[this.props.keyField],
      contentType: 'application/json',
      data: JSON.stringify(update),
      success: function(result) {
        NProgress.done();
        notyf.confirm(self.props.entityName + ' deleted.');
        self.setState({
          data: result,
          tableKey: self.state.tableKey + 1
        });
      },
      error: function(result) {
        NProgress.done();
        notyf.alert('Oops! Something went wrong!');
      }
    });
  };

  editRow = update => {
    update.enabled = true;
    this.setState({
      date: this.state.data
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

  onAddNew = () => {
    this.setState({
      adding: true,
      newKey: this.state.newKey + 1,
      newEntity: this.props.newEntity()
    });
  };

  cancelAdd = () => {
    this.setState({
      adding: false
    });
  };

  add = entity => {
    var self = this;

    this.setState({
      adding: false
    });

    NProgress.start();
    $.ajax({
      type: 'POST',
      url: server + self.props.rest,
      contentType: 'application/json',
      data: JSON.stringify(entity),
      success: function(result) {
        NProgress.done();
        notyf.confirm(self.props.entityName + ' added.');
        self.setState({
          data: result,
          tableKey: self.state.tableKey + 1
        });
      },
      error: function(result) {
        NProgress.done();
        notyf.alert('Oops! Something went wrong!');
      }
    });
  };

  render() {
    var validate = (valid, touched, oneTouched) => {
      if (this.state.newEntity.valid != (touched && valid)) {
        this.state.newEntity.valid = touched && valid;
        this.setState({
          newEntity: this.state.newEntity
        });
      }
    };
    var updateForm = this.props.form(
      'new',
      this.state.newEntity,
      this.state.newKey,
      validate
    );

    return (
      <div class="container">
        <div class="row page-row">
          <div class="col-md-12">
            <EntityIntroduction
              heading={this.props.heading}
              description={this.props.description}
            />
          </div>
        </div>
        <div class="row page-row">
          <div class="col-md-12">
            <button
              class={
                'btn btn-success pull-left' +
                (!this.state.adding ? '' : ' invisible')
              }
              onClick={this.onAddNew}
            >
              <FaPlus /> Add
            </button>
            {(() => {
              if (this.state.adding)
                return (
                  <div class="update-form">
                    <FormHeading heading={'Adding ' + this.props.entityName} />
                    {updateForm}
                    <CreateFormButton
                      data={this.state.newEntity}
                      cancel={this.cancelAdd}
                      add={this.add}
                      enabled={this.state.newEntity.valid}
                    />
                  </div>
                );
            })()}
          </div>
        </div>
        <div class="row page-row">
          <div class="col-md-12">
            <Table
              key={this.state.tableKey}
              keyField={this.props.keyField}
              data={this.state.data}
              columns={this.props.columns}
              expandRow={this.expandRow}
              expandedMap={this.state.expandedMap}
              editRow={this.editRow}
              entityName={this.props.entityName}
              delete={this.delete}
              allowExpand={this.props.allowExpand}
              allowEdit={this.props.allowEdit}
            />
          </div>
        </div>
      </div>
    );
  }
}
