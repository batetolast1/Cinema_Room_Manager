fun parseCardNumber(cardNumber: String): Long {
    val cardNumberRegex = "^\\d{4} \\d{4} \\d{4} \\d{4}$".toRegex()
    val whitespaceRegex = "\\s+".toRegex()

    if (cardNumber.matches(cardNumberRegex)) {
        return cardNumber.replace(whitespaceRegex, "").toLong()
    } else {
        throw CardNumberException()
    }
}

class CardNumberException : RuntimeException()
