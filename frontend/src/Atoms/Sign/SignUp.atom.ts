import { atom } from 'recoil'

const signUpIdTextState = atom({
  key: 'signUpIdTextState',
  default: '',
})

const signUpPwTextState = atom({
  key: 'signUpPwTextState',
  default: '',
})

const signUpCheckPwTextState = atom({
  key: 'signUpCheckPwTextState',
  default: '',
})

const signUpCheckEmailNumberState = atom({
  key: 'signUpCheckEmailNumberState',
  default: '',
})

export { signUpIdTextState, signUpPwTextState, signUpCheckPwTextState, signUpCheckEmailNumberState }
