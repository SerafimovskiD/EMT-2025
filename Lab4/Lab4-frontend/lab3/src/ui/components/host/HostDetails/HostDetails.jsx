import React from 'react';
import {useNavigate, useParams} from "react-router";
import useAuthorDetails from "../../../../hooks/useHostDetails.js";
import PublicIcon from '@mui/icons-material/Public';
import {
    Box,
    Button,
    Chip,
    CircularProgress,
    Grid,
    Typography,
    Paper,
    Avatar,
    Stack,
    Rating,
    Breadcrumbs,
    Link
} from "@mui/material";
import {
    ArrowBack,
    Factory,
    Star,
    ShoppingCart,
    FavoriteBorder,
    Share
} from "@mui/icons-material";
import useCountryDetails from "../../../../hooks/useCountryDetails.js";
import useCountry from "../../../../hooks/useCountry.js";
import useHostDetails from "../../../../hooks/useHostDetails.js";

const HostDetails = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const {host} = useHostDetails(id);//todo: add country

    const { countries, loading: loadingCountries }  = useCountry()
    console.log("Route param id:", id);

    const country = countries.find(c => c.id === host?.countryId);
    console.log("host.countryId =", host?.countryId);
    console.log("countries =", countries);


    if (!host || loadingCountries || !countries) {
        return <CircularProgress />;
    }

    return (
        <Box>
            <Breadcrumbs aria-label="breadcrumb" sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/hosts");
                    }}
                >
                    Hosts
                </Link>
                <Typography color="text.primary">{host.name}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{p: 4, borderRadius: 4}}>
                <Grid container spacing={4}>
                    <Grid size={{xs: 12, md: 9}}>
                        <Box sx={{mb: 3}}>
                            <Typography variant="h5" gutterBottom sx={{fontWeight: 600}}>
                                Host name: {host.name}
                            </Typography>
                            <Typography variant="h5" gutterBottom sx={{fontWeight: 600}}>
                                Host surname: {host.surname}
                            </Typography>
                            {/*<Typography variant="h5" gutterBottom sx={{fontWeight: 600}}>*/}
                            {/*    Country: {country.name}*/}
                            {/*</Typography>*/}
                            <Stack direction="row" spacing={1} sx={{mb: 3}}>
                                <Chip
                                    icon={<PublicIcon />}
                                    label={country?.name}
                                    color="primary"
                                    variant="outlined"
                                    sx={{ p: 2 }}
                                />
                            </Stack>
                        </Box>
                    </Grid>
                    <Grid size={12} display="flex" justifyContent="space-between">
                        <Button
                            variant="outlined"
                            startIcon={<Share/>}
                        >
                            Share
                        </Button>
                        <Button
                            variant="outlined"
                            startIcon={<ArrowBack/>}
                            onClick={() => navigate("/hosts")}
                        >
                            Back to Host
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Box>
    );
};

export default HostDetails;