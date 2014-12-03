@extends ('template')

@section('content')
<h1>Login Page</h1>
	<div class="container">
		<div class="row">
			{{ Form::open(array('url' => 'messagePost')) }}

				<p>
					{{ Form::label('message', 'Message') }}
					{{ Form::text('Type Message') }}
				</p>
				<p>
					{{ Form::label('id', 'User ID') }}
					{{ Form::text('User ID') }}
				</p>

				<p>{{ Form::submit('Submit!') }}</p>
			{{ Form::close() }}

		</div>
	</div>
@stop