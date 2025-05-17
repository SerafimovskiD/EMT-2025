import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import AuthorsGrid from "../../components/host/HostGrid/HostGrid.jsx";
import useHosts from "../../../hooks/useHosts.js";
import AddHostDialog from "../../components/host/AddHostDialog/AddHostDialog.jsx";

const HostsPage = () => {
   const {hosts, loading, onAdd, onEdit, onDelete} = useHosts();
   const [addHostsDialogOpen, setAddHostsDialogOpen] = useState(false);

   return (
       <>
          <Box className="authors-box">
             {loading && (
                 <Box className="progress-box">
                    <CircularProgress/>
                 </Box>
             )}
             {!loading &&
                 <>
                    <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                       <Button variant="contained" color="primary" onClick={() => setAddHostsDialogOpen(true)}>
                          Add Host
                       </Button>
                    </Box>
                    <AuthorsGrid hosts={hosts} onEdit={onEdit} onDelete={onDelete}/>
                 </>}
          </Box>
          <AddHostDialog
              open={addHostsDialogOpen}
              onClose={() => setAddHostsDialogOpen(false)}
              onAdd={onAdd}
          />
       </>
   );
};

export default HostsPage;