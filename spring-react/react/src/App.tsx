import React from 'react';
import MyList from './components/MyList'
import './App.css';

export default function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>spring-react</h1>
      </header>
      <section>
        <MyList />
      </section>
    </div>
  );
}
