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
 * クエリーパラメーターオブジェクトの型です。
 * @param <T> パラメーターの型
 *
 * @see Query
 * @see TypedQuery
 *
 * @since Java Persistence 2.0
 */
public interface Parameter<T> {

    /**
     * パラメーター名を返します。パラメーターが名前付きパラメーターでない場合や名前が割り当てられていない場合はnullを返します。
     * @return パラメーター名
     */
    String getName();

    /**
     * パラメーターの位置を返します。パラメーターが位置指定のパラメーターでない場合はnullを返します。
     * @return パラメーターの位置
     */
    Integer getPosition();

    /**
     * パラメーターのJavaの型を返します。
     * 
     * パラメーターにバインドされた値はこの型に割り当てる必要があります。
     * このメソッドはクライテリアクエリーのみでサポートする必要があります。
     * このメソッドをJPQLやネイティブクエリーで使用するアプリケーションは移植性がありません。
     * @return パラメーターのJavaの型
     * @throws IllegalStateException JPQLやネイティブクエリーでこのメソッドの使用をサポートしていない実装でそれらから得られたパラメーターで実行された場合
     */
     Class<T> getParameterType();
}

