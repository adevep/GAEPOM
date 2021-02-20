import { required, confirmed, length, email, numeric, max } from "vee-validate/dist/rules";
import { extend } from "vee-validate";

// 기본 제공 Rules 시작
extend("required", {
  ...required,
  message: "필수 입력 항목입니다."
});

extend("email", {
  ...email,
  message: "이메일 형식에 맞지 않습니다."
});

extend("confirmed", {
  ...confirmed,
  message: "항목이 일치하지 않습니다."
});

extend("length", {
  ...length,
  message: "허용된 범위를 넘었습니다."
});

extend("numeric", {
  ...numeric,
  message: "숫자만 입력 가능합니다.",
});

extend("max", {
  ...max,
  message: "허용된 범위를 초과했습니다..",
});
// 기본 제공 Rules 끝

// Custom Rules 시작
// ID(최소, 최대)
extend("id-minmax", {
  validate(value, { min, max }) {
    return value.length >= min && value.length <= max;
  },
  params: ['min', 'max'],
  message: "아이디는 6자리 이상, 16자리 이하로 입력해주세요."
});
// ID(소문자, 숫자))
extend("id-char", {
  validate(value) {
    return /^.*(?=.*[a-z]|[0-9]).*$/.test(value);
  },
  message: "알파펫 소문자와 숫자로 구성해주세요."
});

// Password(최소, 최대)
extend("pw-minmax", {
  validate(value, { min, max }) {
    return value.length >= min && value.length <= max;
  },
  params: ['min', 'max'],
  message: "비밀번호는 6자리 이상, 15자리 이하로 입력해주세요."
});
// Password(대문자, 소문자, 숫자, 특수))
extend("pw-char", {
  validate(value) {
    return /^.*(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(value);
  },
  message: "알파벳 대・소문자, 숫자와 특수기호($@$!%*#?&)를 모두 사용하셔야 합니다."
});

// Name(최소, 최대)
extend("name-minmax", {
  validate(value, { min, max }) {
    return value.length >= min && value.length <= max;
  },
  params: ['min', 'max'],
  message: "이름은 실명으로 2자리 이상, 4자리 이하로 입력해주세요."
});
// Name(한글))
extend("name-char", {
  validate(value) {
    return /^.*(?=.*[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).*$/.test(value);
  },
  message: "한글 입력만 허용됩니다. (예시: 홍길동)"
});

// 전화번호(01X-XXXX-XXXX)
extend("phonenum-char", {
  validate(value) {
    return /^.*(?=01.*[01789]-\d{4}-\d{4}).*$/.test(value);
  },
  message: "예시를 참고해주세요.(예시: 01X-XXXX-XXXX)"
});
// Custom Rules 끝
