import {
  TextField,
  IncludeField,
  FormValidator
} from '../../../../components/Forms/Forms.js';

import { textFilter } from 'react-bootstrap-table2-filter';

const columns = [
  {
    dataField: 'name',
    text: 'Product Name',
    filter: textFilter()
  }
];

export default class PortForm extends React.Component {
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
          placeholder="Port Name"
          change={val => (this.props.port.name = val)}
          value={this.props.port.name}
          disabled={!this.props.port.enabled}
          validator={this.validator}
          validation="NAME"
        />
        <IncludeField
          rest="/rest/product"
          keyField="id"
          columns={columns}
          label="Products"
          includeLabel="Produce"
          includedField="products"
          entity={this.props.port}
          disabled={!this.props.port.enabled}
          key={this.props.port.enabled}
        />
      </div>
    );
  }
}
