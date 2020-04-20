import {
  SelectField,
  NumberField,
  FormValidator
} from '../../../../components/Forms/Forms.js';
const NProgress = require('nprogress/nprogress.js');

import server from '../../../../Server.js';

import notyf from '../../../../Notifications.js';

export default class RouteForm extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      ports: []
    };

    this.validator = new FormValidator(this.props.updateForm);
    this.validator.createValidation('PORT1', value => {
      if (!value.toString().trim().length) return 'Port 1 is required.';
      return '';
    });
    this.validator.createValidation('PORT2', value => {
      if (!value.toString().trim().length) return 'Port 2 is required.';
      return '';
    });
    this.validator.createValidation('DISTANCE', value => {
      if (!value.toString().trim().length) return 'Distance is required.';
      if (value <= 0) return 'Please enter a minimum number of 1.';
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

  setPortA = value => {
    for (var z = 0; z < this.state.ports.length; z++) {
      if (this.state.ports[z].id == value) {
        this.props.route.destinationA = this.state.ports[z];
      }
    }
  };

  setPortB = value => {
    for (var z = 0; z < this.state.ports.length; z++) {
      if (this.state.ports[z].id == value) {
        this.props.route.destinationB = this.state.ports[z];
      }
    }
  };

  render() {
    return (
      <div class="form-horizontal">
        <SelectField
          id={() => this.props.formid + '-destinationA'}
          label="Port 1"
          disabled={!this.props.route.enabled}
          keyField="id"
          textField="name"
          data={this.state.ports}
          setOption={this.setPortA}
          value={this.props.route.destinationA.id}
          validator={this.validator}
          validation="PORT1"
        />
        <SelectField
          id={() => this.props.formid + '-destinationB'}
          label="Port 2"
          disabled={!this.props.route.enabled}
          keyField="id"
          textField="name"
          data={this.state.ports}
          setOption={this.setPortB}
          value={this.props.route.destinationB.id}
          validator={this.validator}
          validation="PORT2"
        />
        <NumberField
          id={() => this.props.formid + '-distance'}
          label="Distance (km)"
          change={val => (this.props.route.distance = val)}
          value={this.props.route.distance}
          disabled={!this.props.route.enabled}
          validator={this.validator}
          validation="DISTANCE"
        />
      </div>
    );
  }
}
