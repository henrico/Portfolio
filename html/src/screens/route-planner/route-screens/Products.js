import Entities from './Entities.js';

import ProductForm from './forms/ProductForm.js';
import { textFilter } from 'react-bootstrap-table2-filter';

const columns = [
  {
    dataField: 'name',
    text: 'Name',
    filter: textFilter({
      placeholder: 'Filter Name'
    })
  }
];

export default class Products extends React.Component {
  constructor() {
    super();
    this.state = {
      columns: columns
    };
  }

  form(updateId, product, key, callback) {
    return (
      <ProductForm
        key={key}
        formId={'update-' + updateId}
        product={product}
        updateForm={callback}
      />
    );
  }

  newProduct() {
    return {
      name: '',
      enabled: true,
      valid: false
    };
  }

  render() {
    return (
      <Entities
        columns={this.state.columns}
        keyField="id"
        rest="/rest/product/"
        entityName="Product"
        heading="Products"
        description="Manage your Products then make them available in a Port."
        form={this.form}
        newEntity={this.newProduct}
        allowExpand={true}
        allowEdit={true}
      />
    );
  }
}
