import React, { Component } from 'react';
import GrommetApp from 'grommet/components/App';
import Header from 'grommet/components/Header';
import Title from 'grommet/components/Title';
import Sidebar from 'grommet/components/Sidebar';
import Box from 'grommet/components/Box';
import Menu from 'grommet/components/Menu';
import Button from 'grommet/components/Button';
import Split from 'grommet/components/Split';
import BrightnessIcon from 'react-icons/lib/fa/sun-o';
import ContrastIcon from 'react-icons/lib/fa/adjust'
import FilterIcon from 'react-icons/lib/fa/filter'
import '../node_modules/grommet-css';

class App extends Component {
  render() {
    return (
      <GrommetApp centered={false}>
          <Header fixed={true} colorIndex='neutral-1' justify='center'>
            <Title >RAWFlash</Title>
          </Header>
          <Split priority='right' flex='right' separator={true}>
            <Sidebar colorIndex='neutral-3' size='xsmall'>
              <Box flex='grow' justify='start'>
                <Menu primary={true} fill={true} inline={true} responsive={false}>
                  <Button colorIndex='neutral-2' accent={true} icon={<BrightnessIcon/>} />
                  <Button colorIndex='neutral-2' accent={true} icon={<ContrastIcon/>} />
                  <Button colorIndex='neutral-2' accent={true} icon={<FilterIcon/>} />
                </Menu>
              </Box>
            </Sidebar>
            <Box colorIndex='neutral-2' full='vertical'>
              This is where the image goes
            </Box>
          </Split>
       </GrommetApp>
    );
  }
}

export default App;
