/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: EN 
 */
$.extend( $.validator.messages, {
	required: "This field is required.",
//	remote: "Пожалуйста, введите правильное значение.",
	email: "Please enter correct e-mail.",
//	url: "Пожалуйста, введите корректный URL.",
//	date: "Пожалуйста, введите корректную дату.",
//	dateISO: "Пожалуйста, введите корректную дату в формате ISO.",
//	number: "Пожалуйста, введите число.",
//	digits: "Пожалуйста, вводите только цифры.",
//	creditcard: "Пожалуйста, введите правильный номер кредитной карты.",
//	equalTo: "Пожалуйста, введите такое же значение ещё раз.",
//	extension: "Пожалуйста, выберите файл с правильным расширением.",
	maxlength: $.validator.format( "Please enter at least {0} symbols." ),
	minlength: $.validator.format( "Please enter no more than {0} symbols." ),
//	rangelength: $.validator.format( "Пожалуйста, введите значение длиной от {0} до {1} символов." ),
//	range: $.validator.format( "Пожалуйста, введите число от {0} до {1}." ),
//	max: $.validator.format( "Пожалуйста, введите число, меньшее или равное {0}." ),
//	min: $.validator.format( "Пожалуйста, введите число, большее или равное {0}." )
} );