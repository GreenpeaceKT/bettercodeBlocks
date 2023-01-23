version = "1.1.1"

description = "Appear Syntax highlight!!!!!!"

aliucord {

    changelog.set("""
        # v0.1.0 beta
        * published
        # v1.0.0
        * released
        * diff,md,fixを言語として追加
        * いくつかの言語の色彩を変更
        * 軽微な不具合を修整
        # v1.1.0
        * 言語表示削除
        # v1.1.1
        * mdの色彩を変更
        * fixで色が反映されないバグを修整
        * cssに色彩の要素を追加
        # v1.1.2
        * fixで色が変更されないバグを修整
        * cssに色彩の要素を追加
        * 数字の色彩を変更
        * mdの色彩を変更
        
    """.trimIndent())
    
    excludeFromUpdaterJson.set(false)
}