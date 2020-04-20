import EntityIntroduction from './Introduction/EntityIntroduction.js';

export default class RouteComponent extends React.Component {

  constructor(props){
    super(props);
  }

  render() {

    return (
      <div class="container">
        <Introduction heading = {this.props.heading} description = {this.props.description} />
      </div>
    );

  }

}
