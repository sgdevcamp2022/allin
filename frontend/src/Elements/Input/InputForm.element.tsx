import { RecoilState } from 'recoil'
import InputElement, { InputType } from './Input.element'

type InputForm = {
  className?: string
  inputTextClassName?: string
  formTitleClassName?: string
  warningClassName?: string
  formTitle?: string
  textState: RecoilState<string>
  placeholder?: string
  type?: InputType
  isWarning?: boolean
  warningText?: string
}

const InputForm = ({
  className = '',
  inputTextClassName = '',
  formTitleClassName = '',
  warningClassName = '',
  formTitle,
  textState,
  placeholder = '',
  type = 'text',
  isWarning = false,
  warningText = '',
}: InputForm) => {
  const inputElementClassName = isWarning
    ? `border-border border-[1px] w-full ${inputTextClassName} border-warningText`
    : `border-border border-[1px] w-full ${inputTextClassName}`

  return (
    <div className={'relative ' + className}>
      {formTitle ? <div className={'pb-2 min-w-max ' + formTitleClassName}>{formTitle}</div> : ''}
      {isWarning ? (
        <div
          className={'absolute w-full translate-x-[100%] translate-y-[-160%] ' + warningClassName}
        >
          <div className="w-fit translate-x-[-100%] text-base text-warningText">{warningText}</div>
        </div>
      ) : (
        ''
      )}
      <InputElement
        className={inputElementClassName}
        textState={textState}
        placeholder={placeholder}
        type={type}
      />
    </div>
  )
}

export default InputForm
