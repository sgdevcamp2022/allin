import { atom } from 'recoil'

const signStatusBooleanState = atom({
  key: 'signStatusBooleanState',
  default: false,
})

const signJwtTextState = atom({
  key: 'signJwtTextState',
  default: '',
})

export { signStatusBooleanState, signJwtTextState }
