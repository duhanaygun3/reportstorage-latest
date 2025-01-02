import './App.css';
import { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Report from "./Report";
import AddReport from "./AddReport"; // Doğru dosya yolunu kontrol edin
import EditReport from './EditReport';
import Laborant from './Laborant';
import AddLaborant from './AddLaborant';
import Patients from './Patients';
import AddPatient from './AddPatient';
// import Laborant from "./Laborant"

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact component={Report}/>
            <Route path='/add-report' exact component={AddReport} /> {/* add reprot */}
            <Route path='/reports/edit/:id' component={EditReport} /> {/* Edit sayfası için route */}  
            <Route path='/laborants' exact component={Laborant}/> {/* Laborant */}
            <Route path='/add-laborant' exact component={AddLaborant}/> {/* add laborant */}
            <Route path='/patients' exact component={Patients}/> Laborant
            <Route path='/add-patient' exact component={AddPatient}/> {/* add laborant */}


          </Switch>
        </Router>
    )
  }
  
}

export default App;
