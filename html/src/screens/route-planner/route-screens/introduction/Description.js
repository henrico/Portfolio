export default class Description extends React.Component {

  constructor(props){
    super(props);
  }

  render() {

    return (
      <h4>
        {this.props.description}
      </h4>
    );

  }

}
