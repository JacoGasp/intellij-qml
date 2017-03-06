package name.kropp.intellij.qml

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import name.kropp.intellij.qml.psi.QmlTypes

class QmlSyntaxHighlighter : SyntaxHighlighterBase() {
  companion object {
    val COMMENT = TextAttributesKey.createTextAttributesKey("QML_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
    val KEYWORD = TextAttributesKey.createTextAttributesKey("QML_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
    val TYPE_NAME = TextAttributesKey.createTextAttributesKey("QML_TYPE_NAME", DefaultLanguageHighlighterColors.CLASS_NAME)
    val PROPERTY = TextAttributesKey.createTextAttributesKey("QML_PROPERTY", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    val PARENTHESES = TextAttributesKey.createTextAttributesKey("QML_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
    val COLON = TextAttributesKey.createTextAttributesKey("QML_COLON", DefaultLanguageHighlighterColors.SEMICOLON)
    val STRING = TextAttributesKey.createTextAttributesKey("QML_STRING", DefaultLanguageHighlighterColors.STRING)
    val BOOLEAN = TextAttributesKey.createTextAttributesKey("QML_BOOLEAN", DefaultLanguageHighlighterColors.KEYWORD)
    val NUMBER = TextAttributesKey.createTextAttributesKey("QML_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
    val BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("QML_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

    private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
    private val KEYWORD_KEYS = arrayOf(KEYWORD)
    private val PARENTHESES_KEYS = arrayOf(PARENTHESES)
    private val COLON_KEYS = arrayOf(COLON)
    private val STRING_KEYS = arrayOf(STRING)
    private val BOOLEAN_KEYS = arrayOf(BOOLEAN)
    private val NUMBER_KEYS = arrayOf(NUMBER)
    private val COMMENT_KEYS = arrayOf(COMMENT)
    private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
  }

  override fun getTokenHighlights(tokenType: IElementType) = when(tokenType) {
    QmlTypes.LINE_COMMENT, QmlTypes.BLOCK_COMMENT -> COMMENT_KEYS
    QmlTypes.KEYWORD_IMPORT, QmlTypes.KEYWORD_AS -> KEYWORD_KEYS
    QmlTypes.STRING -> STRING_KEYS
    QmlTypes.INTEGER, QmlTypes.FLOAT -> NUMBER_KEYS
    QmlTypes.TRUE, QmlTypes.FALSE -> BOOLEAN_KEYS
    QmlTypes.LBRACE, QmlTypes.RBRACE -> PARENTHESES_KEYS
    QmlTypes.COLON -> COLON_KEYS
    TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
    else -> EMPTY_KEYS
  }

  override fun getHighlightingLexer() = QmlLexerAdapter()
}