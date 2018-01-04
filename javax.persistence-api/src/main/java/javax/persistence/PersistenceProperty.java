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

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * 単一のコンテナや永続化プロバイダのプロパティを指定します。
 * 
 * {@link PersistenceContext}内で使用してください。
 * 
 * <p> ベンダー固有の設定をプロパティーのSetに含めることができ、エンティティマネージャーが作成された時にコンテナによって永続化プロバイダに渡されます。
 * ベンダーによって認知されていないプロパティは無視されるでしょう。
 *
 * @since Java Persistence 1.0
 */
@Target({})
@Retention(RUNTIME)
public @interface PersistenceProperty {

    /** プロパティの名前 */
    String name();

    /** プロパティの値 */
    String value();

}
