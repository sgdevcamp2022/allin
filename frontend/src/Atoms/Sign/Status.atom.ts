import { atom } from 'recoil'

export const userStatusBooleanState = atom({
  key: 'userStatusState',
  default: false,
})

export const userJwtTextState = atom({
  key: 'userJwtTextState',
  default: '',
})
