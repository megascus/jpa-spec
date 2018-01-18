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

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <code>persistence.xml</code>のキャッシュ要素の値が<code>ENABLE_SELECTIVE</code>か<code>DISABLE_SELECTIVE</code>でキャッシュが有効である場合にエンティティをキャッシュするべきかどうかを指定します。
 * <code>Cacheable</code>アノテーションの値はサブクラスで継承されます。
 * サブクラスで<code>Cacheable</code>を指定することで上書きできます。
 * 
 * <p> <code>Cacheable(false)</code>はエンティティとその状態をプロバイダがキャッシュしてはいけないことを意味します。
 * 
 * @since Java Persistence 2.0
 */
@Target( { TYPE })
@Retention(RUNTIME)
public @interface Cacheable {

    /**
     * (オプション) エンティティをキャッシュする必要があるかどうか。
     */
    boolean value() default true;
}
