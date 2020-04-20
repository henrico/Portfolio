import {
  TextField,
  FormValidator
} from '../../../../components/Forms/Forms.js';

export default class ProductForm extends React.Component {
  constructor(props) {
    super(props);

    this.validator = new FormValidator(this.props.updateForm);
    this.validator.createValidation('NAME', value => {
      if (!value.toString().trim().length) return 'Name is required.';
      return '';
    });
  }

  render() {
    return (
      <div class="form-horizontal">
        <TextField
          id={() => this.props.formid + '-name'}
          label="Name"
          placeholder="Product Name"
          change={val => (this.props.product.name = val)}
          value={this.props.product.name}
          disabled={!this.props.product.enabled}
          validator={this.validator}
          validation="NAME"
        />
      </div>
    );
  }
}
