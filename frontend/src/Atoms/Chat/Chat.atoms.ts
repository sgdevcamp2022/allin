import { atom } from 'recoil'

export type chatType = { sender: string; content: string }

const chatListState = atom({
  key: 'chatListState',
  default: [
    { sender: 'cotmd6203asdf', content: '123' },
    { sender: '2', content: 'abcdefghijklmn' },
  ] as chatType[],
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
