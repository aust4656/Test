JSONObject jsonObject = new JSONObject((String) o);
JSONArray jsonArray = jsonObject.getJSONArray("result");
for (int i = 0; i < jsonArray.length(); i++) {
  JSONObject jsonObject1 = jsonArray.getJSONObject(i);
  tv1.add(jsonObject1.getString("title"));
  tv2.add(jsonObject1.getString("des"));
  lunar.add(jsonObject1.getString("lunar"));
}
