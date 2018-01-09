/*******************************************************************************
 * Copyright (c) 2008 - 2015 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Petros Splinakis - Java Persistence 2.2
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;


/**
 * {@link EntityManagerFactory}とそれに関連する永続化ユニットへの依存関係を表現します。
 *
 * @since Java Persistence 1.0
 */
@Repeatable(PersistenceUnits.class)
@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)
public @interface PersistenceUnit {

    /**
     * (オプション) コンテキスト参照を使用する環境でエンティティマネージャーファクトリーにアクセスする時の名前。DIを使用する場合は不要です。
     */
    String name() default "";

    /**
     * (オプション) <code>persistence.xml</code>ファイルで定義されている永続化ユニットの名前。
     * 
     * これを指定した場合、JNDIでアクセス可能なエンティティーマネージャーファクトリーの永続化ユニットはこれと同じ名前でなければいけません。
     */
    String unitName() default "";

}
