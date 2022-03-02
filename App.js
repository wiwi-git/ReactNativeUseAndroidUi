import React from 'react';
import {Text, TouchableOpacity, View} from 'react-native';
import ImageView from './ImageView';

const App = () => {
  const onClickButtonAction = () => {
    console.log('push');
  };

  return (
    <View
      style={{
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: 'black',
      }}>
      <ImageView
        src={
          'https://eldenring.bn-ent.net/images/information/5537338dc82cf1c5def7de7741acc51a-scaled.jpg'
        }
        style={{width: 100, height: 100}}
      />
      <View
        style={{
          borderWidth: 1,
          alignItems: 'center',
          justifyContent: 'center',
          width: 200,
          height: 30,
          backgroundColor: 'red',
        }}>
        <TouchableOpacity onPress={onClickButtonAction}>
          <Text
            style={{
              textAlign: 'center',
            }}>
            OPEN
          </Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

export default App;
