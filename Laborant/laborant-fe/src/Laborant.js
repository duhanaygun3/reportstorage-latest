import logo from './logo.svg';
import './App.css';
import { Component } from 'react';


class Laborant extends Component {
    

    constructor(props){
        super(props);
        this.state = {
            CreateLaborantRequest:{
                firstName:"",
                lastName:"",
                hospitalIdentityNo:""
          } // State içindeki nesneyi item olarak tanımladık
        };
    }
   

    async componentDidMount() {
        

        console.log(this.props.match.params.id);

     



        // if (this.props.match.params.id !== 'new') {
        //     const client = await (await fetch(`/laborants/${this.props.match.params.id}`)).json();
        //     this.setState({item: client});
        // }
    }

   

    


    async handleSubmit(event) {
        event.preventDefault();
        const { item } = this.state;

        await fetch("api/laborants", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/laborants');
    }

    render() {
        const { CreateLaborantRequest } = this.state;
        return (
          <div className="App">
            <header className="App-header">
              <img src={logo} className="App-logo" alt="logo" />
              <div className="App-intro">
                <h2>create Laborant</h2>
               {CreateLaborantRequest.firstName}
              </div>
            </header>
          </div>
        );
      }
}

export default Laborant;
