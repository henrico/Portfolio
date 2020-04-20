import {
  TextField,
  SelectField,
  NumberField,
  FormValidator
} from '../../../../components/Forms/Forms.js';

const NProgress = require('nprogress/nprogress.js');

import server from '../../../../Server.js';
import notyf from '../../../../Notifications.js';

export default class WarehouseForm extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      ports: []
    };

    this.validator = new FormValidator(this.props.updateForm);
    this.validator.createValidation('NAME', value => {
      if (!value.toString().trim().length) return 'Name is required.';
      return '';
    });
    this.validator.createValidation('PORT', value => {
      if (!value.toString().trim().length) return 'Port is required.';
      return '';
    });
    this.validator.createValidation('TYPE', value => {
      if (!value.toString().trim().length) return 'Type is required.';
      return '';
    });
    this.validator.createValidation('CAPACITY', value => {
      if (value === '') return 'Capacity is requred.';
      if (value > 500) return 'Please enter a maximum number of 500.';
      return '';
    });
    this.validator.createValidation('STORAGE', value => {
      if (value === '') return 'Storage cost is requred.';
      if (value <= 0) return 'Please enter a minimum number of 1.';
      return '';
    });
    this.validator.createValidation('TRANSPORT', value => {
      if (this.props.warehouse.type !== 'EXTERNAL') return '';
      return '';
    });
  }

  componentDidMount = () => {
    var self = this;

    NProgress.start();

    $.ajax({
      url: server + '/rest/port',
      type: 'GET',
      async: false,
      cache: false,
      timeout: 30000,
      success: function(result) {
        NProgress.done();
        self.setState({
          ports: result
        });
      },
      error: function(result) {
        notyf.alert('Oops! Something went wrong!');
        NProgress.done();
      }
    });
  };

  setPort = value => {
    for (var z = 0; z < this.state.ports.length; z++) {
      if (this.state.ports[z].id == value) {
        this.props.warehouse.port = this.state.ports[z];
      }
    }
  };

  setType = value => {
    this.props.warehouse.type = value;
    if (this.props.warehouse.type === 'INTERNAL') {
      this.props.warehouse.transportCost = 0;
    }
    this.setState({});
  };

  render() {
    return (
      <div class="form-horizontal">
        <TextField
          id={() => this.props.formid + '-name'}
          label="Name"
          placeholder="Warehouse Name"
          change={val => (this.props.warehouse.name = val)}
          value={this.props.warehouse.name}
          disabled={!this.props.warehouse.enabled}
          validator={this.validator}
          validation="NAME"
        />
        <SelectField
          id={() => this.props.formid + '-port'}
          label="Port"
          disabled={!this.props.warehouse.enabled}
          keyField="id"
          textField="name"
          data={this.state.ports}
          setOption={this.setPort}
          value={this.props.warehouse.port.id}
          validator={this.validator}
          validation="PORT"
        />
        <SelectField
          id={() => this.props.formid + '-type'}
          label="Type"
          disabled={!this.props.warehouse.enabled}
          keyField="id"
          textField="name"
          data={[
            {
              id: 'INTERNAL',
              name: 'Internal'
            },
            {
              id: 'EXTERNAL',
              name: 'External'
            }
          ]}
          setOption={this.setType}
          value={this.props.warehouse.type}
          validator={this.validator}
          validation="TYPE"
        />
        <NumberField
          id={() => this.props.formid + '-capacity'}
          label="Capacity (crates)"
          change={val => (this.props.warehouse.capacity = val)}
          value={this.props.warehouse.capacity}
          disabled={!this.props.warehouse.enabled}
          validator={this.validator}
          validation="CAPACITY"
          noDecimals={true}
        />
        <NumberField
          id={() => this.props.formid + '-storageCost'}
          label="Storage Cost"
          change={val => (this.props.warehouse.storageCost = val)}
          value={this.props.warehouse.storageCost}
          disabled={!this.props.warehouse.enabled}
          validator={this.validator}
          validation="STORAGE"
        />
        {(() => {
          if (this.props.warehouse.type === 'EXTERNAL')
            return (
              <NumberField
                id={() => this.props.formid + '-transportCost'}
                label="Transport Cost"
                change={val => (this.props.warehouse.transportCost = val)}
                value={this.props.warehouse.transportCost}
                disabled={!this.props.warehouse.enabled}
                validator={this.validator}
                validation="TRANSPORT"
              />
            );
          else {
            this.validator.setValue('TRANSPORT', '');
            this.validator.validate('TRANSPORT');
          }
        })()}
      </div>
    );
  }
}
