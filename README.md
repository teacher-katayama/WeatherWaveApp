# WeatherWaveApp
複数の地域の今日の天気、風向き、波の高さを表示するアプリ
# 困っています。助けてください。
- 処理が途中で止まってしまい、困っています。原因が判りません。指定した全部の地域の情報を表示するようにしてください。
- 対象地域は`TARGETS`に定義しています。
- 2025/6/4時点の実行結果は以下の通りです。 
```
PS C:\Users\katayama\Desktop\WeatherWaveApp>  c:; cd 'c:\Users\katayama\Desktop\WeatherWaveApp'; & 'C:\Users\katayama\Bin\jdk-23.0.2\bin\java.exe' '-agentlib:jdwp=transport=dt_socket,server=n,suspend=y,address=localhost:54298' '@C:\Users\katayama\AppData\Local\Temp\cp_80ne0tuqf6fgk3a149mjgbein.argfile' 'WeatherWaveApp' 
【函館】くもり 所により 雨 で 雷を伴う。風向きは南西の風 後 やや強く。波の高さは1.5メートル 後 2メートル。
【青森】くもり 所により 雨 で 雷を伴う。風向きは西の風 やや強く 後 南西の風 やや強く。波の高さは1メートル 後 2メートル。
【東京】くもり 昼過ぎ から 夕方 晴れ 所により 夕方 から 雨 で 雷を伴う。風向きは南の風 後 北の風。波の高さは0.5メートル。
Exception in thread "main" org.json.JSONException: JSONObject["waves"] not found.
        at org.json.JSONObject.get(JSONObject.java:652)
        at org.json.JSONObject.getJSONArray(JSONObject.java:845)
        at WeatherWaveApp.main(WeatherWaveApp.java:49)
PS C:\Users\katayama\Desktop\WeatherWaveApp> 
```
# 開発環境の構築手順
- JSON In Javaライブラリが必要です。
1. ブラウザで`https://mvnrepository.com/artifact/org.json/json/20250107`にアクセスします。
2. `Files`セクションの`bundle`リンクをクリックして`json-20250107.jar`ファイルをダウンロードします。  
ダウンロードが済んだら、ブラウザは閉じます。  
⚠️注意
ブラウザによっては「json-20250107.jarはデバイスに問題を起こす可能性があります。このまま保存しますか？」というポップアップが表示されることがあります。この場合は「保存」をクリックしてください。
3. ダウンロードした`json-20250107.jar`を`WeatherWaveApp`フォルダに移動します。
