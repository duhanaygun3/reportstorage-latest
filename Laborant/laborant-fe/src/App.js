import './App.css';
import { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Report from "./Report";
import AddReport from "./AddReport"; // Doğru dosya yolunu kontrol edin
import EditReport from './EditReport';

// import Laborant from "./Laborant"

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact component={Report}/>
            <Route path='/add-report' exact component={AddReport} /> {/* Yeni rota */}
            <Route path="/reports/edit/:id" component={EditReport} /> {/* Edit sayfası için route */}  
          </Switch>
        </Router>
    )
  }
  
}

export default App;
