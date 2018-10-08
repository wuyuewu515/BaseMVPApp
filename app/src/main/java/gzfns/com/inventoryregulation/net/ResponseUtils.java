package gzfns.com.inventoryregulation.net;

/**
 * Created by Administrator on 2018/6/15.
 */

class ResponseUtils {
//    static void outLogin(JSONObject jsonObject) throws JSONException {
//        final Activity activity = AppManager.currentActivity();
//        AppApplication.isAutoLogin = false;
//        if (activity != null && !activity.isFinishing()) {
//            String msg = jsonObject.getString("message");
//            new EcarDialog(activity).setTextModel(EcarDialog.ONE_TXT)
//                    .setTextFirst(msg)
//                    .setBtnModel(EcarDialog.ONE_BTN)
//                    .setBtnListener(new EcarDialog.OnClickBtnListener() {
//                        @Override
//                        public void onClick(EcarDialog ecarDialog, View view) {
//                            AppApplication application = (AppApplication) activity.getApplication();
//                            application.stopUploadService();
//
//                            List<Account> list = DbUtils.getDaoSession().getAccountDao().queryBuilder().where(AccountDao.Properties.IsActivity.eq(1)).list();
//                            if(list != null && list.size() > 0){
//                                for(Account account : list){
//                                    account.setIsActivity(0);
//                                    account.saveOrUpdata();
//                                }
//                            }
//
//                            application.setAccount(null);
//                            AppManager.finishAllActivity();
//                            Intent intent = new Intent(application, MainActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            application.startActivity(intent);
//                            ecarDialog.dismiss();
//                        }
//                    }).show();
//        } else {
//            AppApplication application = (AppApplication) activity.getApplication();
//            application.stopUploadService();
//            AppManager.finishAllActivity();
//            Intent intent = new Intent(application, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            application.startActivity(intent);
//        }
//
//    }
}
