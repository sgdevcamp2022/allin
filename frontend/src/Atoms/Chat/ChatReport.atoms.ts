import { atom } from 'recoil'
import { chatType } from './Chat.atoms'

export type chatReportType = {
  topicId: string
  reportedUser: string
  message: string
  reason: chatReportReason
}

export type chatReportReason =
  | 'SPAM_OR_DUPLICATE_WRITING'
  | 'PORNOGRAPHY'
  | 'ILLEGAL_INFORMATION'
  | 'HARM_TO_ADOLESCENTS'
  | 'SWEAR_WORD_OR_LIFE_THREAT_OR_HATE_EXPRESSION_OR_DISCRIMINATORY_EXPRESSIO'
  | 'PERSONAL_INFORMATION_DISCLOSURE'
  | 'UNPLEASANT_EXPRESSION'
  | 'null'

type CHAT_REPORT_REASON = {
  [chatReportReason: string]: string
}

export const CHAT_REPORT_REASON: CHAT_REPORT_REASON = {
  SPAM_OR_DUPLICATE_WRITING: '스팸홍보/도배글입니다.',
  PORNOGRAPHY: '음란물입니다.',
  ILLEGAL_INFORMATION: '불법정보를 포함하고 있습니다.',
  HARM_TO_ADOLESCENTS: '청소년에게 유해한 내용입니다.',
  SWEAR_WORD_OR_LIFE_THREAT_OR_HATE_EXPRESSION_OR_DISCRIMINATORY_EXPRESSIO:
    '욕설/생명경시/혐오/차별적 표현입니다.',
  PERSONAL_INFORMATION_DISCLOSURE: '개인정 노출 게시물입니다',
  UNPLEASANT_EXPRESSION: '불쾌한 표현이 있습니다.',
}

export const chatReportState = atom({
  key: 'chatReportState',
  default: {} as chatReportType,
})

export const chatReportPopupedBooleanState = atom({
  key: 'chatReportPopupedBooleanState',
  default: false,
})

export const chatReportChatState = atom({
  key: 'chatReportChatState',
  default: {} as chatType,
})
