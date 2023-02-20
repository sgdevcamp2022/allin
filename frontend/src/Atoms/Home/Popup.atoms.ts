import { atom } from 'recoil'

const popupTitleTextStatus = atom({
  key: 'popupTitleTextStatus',
  default: '',
})

const popupSubTitleTextStatus = atom({
  key: 'popupSubTitleTextStatus',
  default: '',
})

const popupedBooleanStatus = atom({
  key: 'popupedBooleanStatus',
  default: false,
})

export { popupSubTitleTextStatus, popupTitleTextStatus, popupedBooleanStatus }
