import { RecoilState } from 'recoil'
import InputElement, { InputType } from './Input.element'

type InputForm = {
  className?: string
  inputTextClassName?: string
  formTitleClassName?: string
  formTitle?: string
  textState: RecoilState<string>
  placeholder?: string
  type?: InputType
}

const InputForm = ({
  className = '',
  inputTextClassName = '',
  formTitleClassName = '',
  formTitle,
  textState,
  placeholder = '',
  type = 'text',
}: InputForm) => {
  return (
    <div className={className}>
      {formTitle ? (
        <div className={'pb-2 min-w-max ' + { formTitleClassName }}>{formTitle}</div>
      ) : (
        ''
      )}
      <InputElement
        className={'border-border border-[1px] w-full ' + inputTextClassName}
        textState={textState}
        placeholder={placeholder}
        type={type}
      />
    </div>
  )
}

export default InputForm
