import { atom } from 'recoil'

const popupTitleTextStatus = atom({
  key: 'popupTitleTextStatus',
  default: '',
})

const popupSubTitleTextStatus = atom({
  key: 'popupSubTitleTextStatus',
  default: '',
})

const PopupedBooleanStatus = atom({
  key: 'PopupedBooleanStatus',
  default: false,
})

export { popupSubTitleTextStatus, popupTitleTextStatus, PopupedBooleanStatus }
