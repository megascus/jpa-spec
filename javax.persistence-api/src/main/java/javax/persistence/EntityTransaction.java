/*******************************************************************************
 * Copyright (c) 2008 - 2013 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence;

/**
 * リソースローカルエンティティマネージャー上のトランザクションを制御するために使用されるインタフェースです。
 * 
 * {@link EntityManager#getTransaction EntityManager.getTransaction()}メソッドは<code>EntityTransaction</code>インターフェイスを返します。
 *
 * @since Java Persistence 1.0
 */
public interface EntityTransaction {

     /**
      * リソーストランザクションを開始します。
      * @throws IllegalStateException <code>isActive()</code>がtrueの場合
      */
     public void begin();

     /**
      * 現在のリソーストランザクションをコミットし、すべてのフラッシュされていない変更をデータベースに書き込みます。
      * @throws IllegalStateException <code>isActive()</code>がfalseの場合
      * @throws RollbackException コミットに失敗した場合
      */
     public void commit();

     /**
      * 現在のリソーストランザクションをロールバックします。
      * @throws IllegalStateException <code>isActive()</code>がfalseの場合
      * @throws PersistenceException 予期しないエラー状態に遭遇した場合
      */
     public void rollback();

     /**
      * 現在のリソーストランザクションがロールバックのみ可能になるようにマークします。
      * @throws IllegalStateException <code>isActive()</code>がfalseの場合
      */
     public void setRollbackOnly();

     /**
      * トランザクションにロールバックのマークが付けられているかを確認します。
      * @return トランザクションにロールバックのマークが付けられているかを示すboolean
      * @throws IllegalStateException <code>isActive()</code>がfalseの場合
      */
     public boolean getRollbackOnly();

     /**
      * トランザクションが進行中かどうかを示します。
      * @return トランザクションが進行中かどうかを示すboolean
      * @throws PersistenceException 予期しないエラー状態に遭遇した場合
      */
     public boolean isActive();
}
