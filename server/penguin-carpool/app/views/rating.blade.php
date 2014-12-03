@extends ('template')

@section('content')
<h1>Rating Page</h1>
	<div class="container">
		<div class="row">
			{{ Form::open(array('url' => 'rate')) }}

				<p>
					{{ Form::label('rate', 'Rate') }}
					{{ Form::text('rate') }}
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