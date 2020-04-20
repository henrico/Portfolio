import {
  TextField,
  NumberField,
  FormValidator
} from '../../../../components/Forms/Forms.js';

export default class ShipForm extends React.Component {
  constructor(props) {
    super(props);

    this.validator = new FormValidator(this.props.updateForm);
    this.validator.createValidation('NAME', value => {
      if (!value.toString().trim().length) return 'Name is required.';
      return '';
    });
    this.validator.createValidation('SPEED', value => {
      if (value === '') return 'Speed is requred.';
      if (value <= 0) return 'Please enter a minimum number of 1.';
      if (value > 20) return 'Please enter a maximum number of 20.';
      return '';
    });
    this.validator.createValidation('CAPACITY', value => {
      if (value === '') return 'Capacity is requred.';
      if (value <= 0) return 'Please enter a minimum number of 1.';
      if (value > 20) return 'Please enter a maximum number of 20.';
      return '';
    });
  }

  render() {
    return (
      <div class="form-horizontal">
        <TextField
          id={() => this.props.formid + '-name'}
          label="Name"
          placeholder="Ship Name"
          change={val => (this.props.ship.name = val)}
          value={this.props.ship.name}
          disabled={!this.props.ship.enabled}
          validator={this.validator}
          validation="NAME"
        />
        <NumberField
          id={() => this.props.formid + '-speed'}
          label="Speed (km/day)"
          change={val => (this.props.ship.speed = val)}
          value={this.props.ship.speed}
          disabled={!this.props.ship.enabled}
          validator={this.validator}
          validation="SPEED"
        />
        <NumberField
          id={() => this.props.formid + '-capacity'}
          label="Capacity (crates)"
          change={val => (this.props.ship.capacity = val)}
          value={this.props.ship.capacity}
          disabled={!this.props.ship.enabled}
          validator={this.validator}
          validation="CAPACITY"
          noDecimals={true}
        />
      </div>
    );
  }
}
