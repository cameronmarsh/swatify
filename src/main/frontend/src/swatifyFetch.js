export default function swatifyFetch(url, options = null) {
  let includeCookiesOption = { credentials: "same-origin" };

  if (options) {
    options = Object.assign(options, includeCookiesOption);
  } else {
    options = includeCookiesOption;
  }

  return fetch(url, options);
}
