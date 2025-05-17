import React from 'react';
import { useNavigate, useParams } from "react-router";
import useAccommodationDetails from "../../../../hooks/useAccomodationsDetails.js";
import {
    Box,
    Button,
    Breadcrumbs,
    CircularProgress,
    Grid,
    Link,
    Paper,
    Typography
} from "@mui/material";
import {
    ArrowBack,
    Share
} from "@mui/icons-material";

const AccommodationDetails = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const { accommodation } = useAccommodationDetails(id);

    if (!accommodation) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh' }}>
                <CircularProgress />
            </Box>
        );
    }

    return (
        <Box>
            <Breadcrumbs aria-label="breadcrumb" sx={{ mb: 3 }}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/accommodations");
                    }}
                >
                    Accommodations
                </Link>
                <Typography color="text.primary">{accommodation.name}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{ p: 4, borderRadius: 4 }}>
                <Grid container spacing={4}>
                    <Grid item xs={12} md={9}>
                        <Box sx={{ mb: 3 }}>
                            <Typography variant="h5" gutterBottom sx={{ fontWeight: 600 }}>
                                Accommodation Name: {accommodation.name}
                            </Typography>
                            <Typography variant="h6" gutterBottom>
                                Category: {accommodation.category}
                            </Typography>
                            <Typography variant="h6" gutterBottom>
                                Host: {accommodation.host ? `${accommodation.host.name} ${accommodation.host.surname}` : "Unknown"}
                            </Typography>
                            <Typography variant="h6" gutterBottom>
                                Number of Rooms: {accommodation.numRooms}
                            </Typography>
                        </Box>
                    </Grid>
                    <Grid item xs={12} display="flex" justifyContent="space-between">
                        <Button
                            variant="outlined"
                            startIcon={<Share />}
                        >
                            Share
                        </Button>
                        <Button
                            variant="outlined"
                            startIcon={<ArrowBack />}
                            onClick={() => navigate("/accommodations")}
                        >
                            Back to Accommodations
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Box>
    );
};

export default AccommodationDetails;
