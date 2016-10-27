# JMetricsCounters

ソフトウェアメトリクス測定(java)クラスです。

それぞれstaticメソッド"Class.count(Path path)"を使用し、"long"型の戻り値を返します。
ファイル操作でエラーが発生した場合は、"-1"を返します。

## Implemented Modules

+ Lines of Code (LOC)
    + コード数。デフォルトでは空行を省くようにしていますが、.counterTotal(path)で空行込みの実行数を返します。
+ Cyclomatic Complexity (McCabeのComplexity)
    + 分岐数。単純にif/else if, switchのcase, for, whileなどをカウントして+1 するだけのものです。正確には例外処理やStreamAPIなども測定すべきだとは思いますが...
    + デフォルトでは、AND"&&"/OR"||"を含んでもカウントが1つと数えるType1を用いています、Type2も実装済みです。ただし、そちらは特に遅い。

## Planned Modules 

+ Number of Levels
+ Couplings
+ Lack of Choseion of Methods

and more...
