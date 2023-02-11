import { atom } from 'recoil'

export type chatType = { sender: string; content: string }

const chatListState = atom({
  key: 'chatListState',
  default: [] as chatType[],
})

const chatSendStringState = atom({
  key: 'chatSendStringState',
  default: '',
})

const chatBlockBooleanState = atom({
  key: 'chatBlockBooleanState',
  default: false,
})

export { chatListState, chatSendStringState, chatBlockBooleanState }
