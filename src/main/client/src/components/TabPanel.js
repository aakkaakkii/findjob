import React from 'react';
import Box from "@material-ui/core/Box";

const TabPanel = ({ children, value, index }) => {

    return (
        <div>
            {value === index && (
                <Box>
                    {children}
                </Box>
            )}
        </div>
    );
}


export default TabPanel;