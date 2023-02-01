import { RecoilState } from 'recoil'
import { InpuSecretTextElement, InputTextElement } from './InputText.element'

const InputTextForm = ({
  className = '',
  inputTextClassName = '',
  formTitle,
  textState,
}: {
  className?: string
  inputTextClassName?: string
  formTitle?: string
  textState: RecoilState<string>
}) => {
  return (
    <div className={'flex flex-col ' + className}>
      {formTitle ? <div className="w-full pb-2">{formTitle}</div> : ''}
      <InputTextElement
        className={'border-border border-[1px] w-full ' + inputTextClassName}
        textState={textState}
      />
    </div>
  )
}

const InputSecretTextForm = ({
  className = '',
  inputTextClassName = '',
  formTitle,
  textState,
}: {
  className?: string
  inputTextClassName?: string
  formTitle?: string
  textState: RecoilState<string>
}) => {
  return (
    <div className={'flex flex-col ' + className}>
      {formTitle ? <div className="w-full pb-2">{formTitle}</div> : ''}
      <InpuSecretTextElement
        className={'border-border border-[1px] w-full ' + inputTextClassName}
        textState={textState}
      />
    </div>
  )
}

export { InputTextForm, InputSecretTextForm }
