import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from "./ui/components/layout/Layout/Layout.jsx";
import AccommodationsPage from "./ui/pages/AccommodationsPage/AccommodationsPage.jsx";
import HostPage from "./ui/pages/HostPage/HostsPage.jsx";
import CountryPage from "./ui/pages/CountryPage/CountryPage.jsx";
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import CountryDetails from "./ui/components/country/CountryDetails/CountryDetails.jsx";
import HostDetails from "./ui/components/host/HostDetails/HostDetails.jsx";
import AccommodationDetails from "./ui/components/accommodation/AccommodationDetails/AccommodationDetails.jsx";


const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<HomePage />} />
                    <Route path="accommodations" element={<AccommodationsPage />} />
                    <Route path="/accommodations/:id" element={<AccommodationDetails />} />
                    <Route path="hosts" element={<HostPage/>} />
                    <Route path="/hosts/:id" element={<HostDetails />} />
                    <Route path="countries" element={<CountryPage />} />
                    <Route path="countries/:id" element={<CountryDetails/>}/>
                </Route>
            </Routes>
        </Router>
    );
};
export default App;
