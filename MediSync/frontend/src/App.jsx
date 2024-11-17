import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import { useState } from 'react';
import Login from './Login';
import './App.css';
import Header from './Header';
import Patients from './Patients';
import Dashboard from './Dashboard';
import CodeVerification from './CodeVerification';
import DischargePatient from './DischargePatient';
import PatientInfo from './PatientInfo';
import HealthOverview from './HealthOverview';

function App() {
  return (
    <Router>
      <Main />
    </Router>
  );
}

function Main() {
  const location = useLocation();
  const [showModal, setShowModal] = useState(true);
  const headerExcludedRoutes = ['/', '/verify'];
  const shouldShowHeader = !headerExcludedRoutes.includes(location.pathname);

  return (
    <>
      {shouldShowHeader && <Header> {}
        <Routes>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/patients" element={<Patients />} />
          <Route path="/patients/:id" element={<HealthOverview />} />
          <Route path="/park" element={<DischargePatient showModal={showModal} setShowModal={setShowModal}/>} />
        </Routes>
      </Header>}
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/verify" element={<CodeVerification />} />
      </Routes>
    </>
  );
}

export default App;

